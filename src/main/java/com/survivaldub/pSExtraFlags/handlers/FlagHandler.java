package com.survivaldub.pSExtraFlags.handlers;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;

public class FlagHandler {
    public static StateFlag PREVENT_TELEPORT;
    public static StateFlag PROTECT_ANIMALS;

    public static void registerFlags() {
        block3: {
            FlagRegistry flagRegistry = WorldGuard.getInstance().getFlagRegistry();
            try {
                PREVENT_TELEPORT = new StateFlag("prevent-teleport", true);
                flagRegistry.register((Flag)PREVENT_TELEPORT);
                PROTECT_ANIMALS = new StateFlag("protect-animals", true);
                flagRegistry.register((Flag)PROTECT_ANIMALS);
            } catch (FlagConflictException e) {
                Flag existing = flagRegistry.get("prevent-teleport");
                if (existing instanceof StateFlag) {
                    PREVENT_TELEPORT = (StateFlag)existing;
                }
                if (!((existing = flagRegistry.get("protect-animals")) instanceof StateFlag)) break block3;
                PROTECT_ANIMALS = (StateFlag)existing;
            }
        }
    }
}
