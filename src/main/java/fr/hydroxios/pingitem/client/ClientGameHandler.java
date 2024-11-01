package fr.hydroxios.pingitem.client;

import fr.hydroxios.pingitem.PingItem;
import fr.hydroxios.pingitem.network.PingItemPacketPayload;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = PingItem.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientGameHandler {

    @SubscribeEvent
    public static void onKeyInput(ScreenEvent.KeyPressed.Pre e){
        if(e.getKeyCode() == ClientModHandler.chatItemKey.getKey().getValue()){
            if(e.getScreen() instanceof AbstractContainerScreen<?> s){
                if(s.getSlotUnderMouse() != null){
                    ItemStack is = s.getSlotUnderMouse().getItem();
                    if(!is.isEmpty()){
                        LocalPlayer p = e.getScreen().getMinecraft().player;
                        Component c = p.getDisplayName().copy().append(": ").append(is.getHighlightTip(is.getDisplayName()));
                        PacketDistributor.sendToServer(new PingItemPacketPayload(c));
                    }
                }
            }
        }
    }

}
