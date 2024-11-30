package com.survivaldub.pSExtraFlags.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.survivaldub.pSExtraFlags.PSExtraFlags;
import com.survivaldub.pSExtraFlags.handlers.FlagHandler;
import com.survivaldub.pSExtraFlags.utils.ColorUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.audience.Audience;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AnimalProtectionListener implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getDamager();
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player)entity;
        if (player.hasPermission("protectionstones.admin")) {
            return;
        }
        Entity target = event.getEntity();
        if (!(target instanceof Mob)) {
            return;
        }
        Location location = event.getEntity().getLocation();
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionManager = container.get(BukkitAdapter.adapt((World)location.getWorld()));
        if (regionManager != null) {
            ApplicableRegionSet regionSet = regionManager.getApplicableRegions(BukkitAdapter.asBlockVector((Location)location));
            for (ProtectedRegion region : regionSet) {
                StateFlag.State flagState = (StateFlag.State)region.getFlag((Flag) FlagHandler.PROTECT_ANIMALS);
                if (flagState != StateFlag.State.ALLOW || region.getMembers().contains(player.getUniqueId()) || region.getOwners().contains(player.getUniqueId()) || !(target instanceof Animals)) continue;
                event.setCancelled(true);
                String fileMessage = PSExtraFlags.getInstance().getFiles().getMessages().getString("NO-ANIMAL-KILL");
                Component message = ColorUtils.translate(fileMessage);
                Audience audience = (Audience)player;
                audience.sendMessage(message);
                return;
            }
        }
    }
}