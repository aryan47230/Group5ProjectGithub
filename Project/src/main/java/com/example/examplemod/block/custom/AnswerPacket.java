package com.example.examplemod.block.custom;

public record AnswerPacket(int choiceIndex) {

    public static final ResourceLocation ID =
        ResourceLocation.fromNamespaceAndPath(ModRegistry.MOD_ID, "answer_packet");

    public static void encode(AnswerPacket pkt, FriendlyByteBuf buf) {
        buf.writeInt(pkt.choiceIndex());
    }

    public static AnswerPacket decode(FriendlyByteBuf buf) {
        return new AnswerPacket(buf.readInt());
    }

    public static void handle(AnswerPacket pkt, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();
            if (player.containerMenu instanceof ComputerMenu menu) {
                Level level = player.level();
                BlockEntity be = level.getBlockEntity(menu.getBlockPos());
                if (be instanceof ComputerBlockEntity computer) {
                    // Check stored correct answer (see Screen section)
                    int correct = ((ComputerScreen) Minecraft.getInstance().screen).getCurrentCorrectIndex();
                    if (pkt.choiceIndex() == correct) {
                        computer.rewardPlayer(player);
                    }
                }
            }
        });
    }
}