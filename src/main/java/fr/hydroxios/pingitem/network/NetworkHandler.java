package fr.hydroxios.pingitem.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NetworkHandler {

    @SubscribeEvent
    public static void registerPackets(RegisterPayloadHandlersEvent e){
        PayloadRegistrar registrar = e.registrar("1");
        registrar.playBidirectional(
                PingItemPacketPayload.TYPE,
                PingItemPacketPayload.STREAM_CODEC,
                new DirectionalPayloadHandler<>(

                        //Client
                        (payload, context) -> {
                            context.enqueueWork(() -> {
                               context.player().sendSystemMessage(payload.component());
                            });
                        },

                        //Server
                        (payload, context) -> {
                            context.enqueueWork(() -> {
                                PacketDistributor.sendToAllPlayers(payload);
                            });
                        }
                )
        );
    }

}
