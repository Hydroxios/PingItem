package fr.hydroxios.pingitem.client;

import fr.hydroxios.pingitem.PingItem;
import fr.hydroxios.pingitem.compat.JEIPingItem;
import fr.hydroxios.pingitem.network.NetworkHandler;
import fr.hydroxios.pingitem.network.packets.ChatItemC2SPacket;
import mezz.jei.api.ingredients.ITypedIngredient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = PingItem.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key e) {
        if (e.getKey() == ModHandler.chatItemKey.getKey().getValue() && e.getAction() == GLFW.GLFW_PRESS) {
            if (Minecraft.getInstance().screen instanceof AbstractContainerScreen screen) {
                if (screen.getSlotUnderMouse() != null) {
                    ItemStack hoveredItem = screen.getSlotUnderMouse().getItem();
                    if (!hoveredItem.isEmpty()) {
                        NetworkHandler.INSTANCE.sendToServer(new ChatItemC2SPacket(hoveredItem.getHighlightTip(hoveredItem.getDisplayName())));
                    }
                }
            }
            if(ModList.get().isLoaded("jei")){
                if (JEIPingItem.jeiRuntime != null) {
                    Optional<ITypedIngredient<?>> o = JEIPingItem.jeiRuntime.getIngredientListOverlay().getIngredientUnderMouse();
                    o.ifPresent((i) -> {
                        i.getItemStack().ifPresent(is -> {
                            NetworkHandler.INSTANCE.sendToServer(new ChatItemC2SPacket(is.getHighlightTip(is.getDisplayName())));
                        });
                    });
                }
            }
        }
    }

}
