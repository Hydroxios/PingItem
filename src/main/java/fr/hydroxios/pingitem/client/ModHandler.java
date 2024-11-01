package fr.hydroxios.pingitem.client;

import com.mojang.blaze3d.platform.InputConstants;
import fr.hydroxios.pingitem.PingItem;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PingItem.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModHandler {
    public static final KeyMapping chatItemKey = new KeyMapping("key." + PingItem.MODID + ".ping_item", KeyConflictContext.GUI, InputConstants.getKey(InputConstants.KEY_T, -1), KeyMapping.CATEGORY_INVENTORY);

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent e){
        e.register(chatItemKey);
    }

}
