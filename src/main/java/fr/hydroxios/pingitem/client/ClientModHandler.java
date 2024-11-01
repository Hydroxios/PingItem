package fr.hydroxios.pingitem.client;

import com.mojang.blaze3d.platform.InputConstants;
import fr.hydroxios.pingitem.PingItem;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;

@EventBusSubscriber(modid = PingItem.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModHandler {

    public static final KeyMapping chatItemKey = new KeyMapping("key." + PingItem.MODID + ".ping_item", KeyConflictContext.GUI, InputConstants.getKey(InputConstants.KEY_T, -1), KeyMapping.CATEGORY_INVENTORY);

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent e){
        e.register(chatItemKey);
    }
}
