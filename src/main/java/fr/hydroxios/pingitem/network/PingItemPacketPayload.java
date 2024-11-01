package fr.hydroxios.pingitem.network;

import fr.hydroxios.pingitem.PingItem;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record PingItemPacketPayload(Component component) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<PingItemPacketPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(PingItem.MODID, "ping_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PingItemPacketPayload> STREAM_CODEC = StreamCodec.composite(
            ComponentSerialization.STREAM_CODEC,
            PingItemPacketPayload::component,
            PingItemPacketPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
