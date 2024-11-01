package fr.hydroxios.pingitem.network;

import fr.hydroxios.pingitem.PingItem;
import fr.hydroxios.pingitem.network.packets.ChatItemC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {

    public static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    @SubscribeEvent
    public static void registerPackets(){
        SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(PingItem.MODID, "network"),
                () -> "1.0",
                s -> true,
                s -> true
        );

        INSTANCE = CHANNEL;

        CHANNEL.messageBuilder(ChatItemC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ChatItemC2SPacket::new)
                .encoder(ChatItemC2SPacket::toBytes)
                .consumerMainThread(ChatItemC2SPacket::handle)
                .add();
    }

}
