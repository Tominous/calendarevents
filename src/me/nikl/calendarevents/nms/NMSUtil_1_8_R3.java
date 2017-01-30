package me.nikl.calendarevents.nms;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Created by niklas on 10/17/16.
 *
 * nms util for 1.8.R3
 */
public class NMSUtil_1_8_R3 implements NMSUtil {
	
	@Override
	public void sendTitle(Player player, String title, String subTitle) {
		if(title != null){
			IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&',title + "\"}"));
			PacketPlayOutTitle pTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(pTitle);
			
		}
		if(subTitle != null){
			IChatBaseComponent chatSubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&',subTitle + "\"}"));
			PacketPlayOutTitle pSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubTitle);
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(pSubTitle);
			
		}
		PacketPlayOutTitle length = new PacketPlayOutTitle(5, 20, 5);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
	}
	
	@Override
	public void sendActionbar(Player p, String message) {
		
		IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&',message + "\"}"));
		
		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
		
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}
	
	
	
	@Override
	public void sendListFooter(Player player, String footer){
		IChatBaseComponent bottom = IChatBaseComponent.ChatSerializer.a("{text: '" + ChatColor.translateAlternateColorCodes('&', footer) + "'}");
		
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		
		try{
			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, bottom);
			footerField.setAccessible(false);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	
	@Override
	public void sendListHeader(Player player, String header){
		IChatBaseComponent bottom = IChatBaseComponent.ChatSerializer.a("{text: '" + ChatColor.translateAlternateColorCodes('&', header) + "'}");
		
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		
		try{
			Field footerField = packet.getClass().getDeclaredField("a");
			footerField.setAccessible(true);
			footerField.set(packet, bottom);
			footerField.setAccessible(false);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
}