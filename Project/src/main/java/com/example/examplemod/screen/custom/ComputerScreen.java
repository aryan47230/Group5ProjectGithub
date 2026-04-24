package com.example.examplemod.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import com.example.examplemod.screen.custom.ComputerMenu;

@OnlyIn(Dist.CLIENT)
public class ComputerScreen extends AbstractContainerScreen<ComputerMenu> {

    private QuestionBank.Question currentQuestion;
    private int currentCorrectIndex;
    private String feedback = "";
    private int feedbackColor = 0xFFFFFF;

    public ComputerScreen(ComputerMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 256;
        this.imageHeight = 200;
        loadNewQuestion();
    }

    private void loadNewQuestion() {
        currentQuestion = QuestionBank.getRandom();
        currentCorrectIndex = currentQuestion.correctIndex();
        feedback = "";
    }

    public int getCurrentCorrectIndex() { return currentCorrectIndex; }

    @Override
    protected void init() {
        super.init();
        int x = leftPos;
        int y = topPos;

        // Add a button per choice
        for (int i = 0; i < currentQuestion.choices().length; i++) {
            final int idx = i;
            addRenderableWidget(Button.builder(
                Component.literal((char)('A' + i) + ") " + currentQuestion.choices()[i]),
                btn -> onChoiceSelected(idx)
            ).bounds(x + 10, y + 80 + i * 24, 236, 20).build());
        }
    }

    private void onChoiceSelected(int idx) {
        if (idx == currentCorrectIndex) {
            feedback = "Correct! Item awarded.";
            feedbackColor = 0x55FF55;
            // Send packet to server
            PacketHandler.CHANNEL.sendToServer(new AnswerPacket(idx));
        } else {
            feedback = "Wrong! Try again.";
            feedbackColor = 0xFF5555;
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // Dark terminal background
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF1A1A2E);
        // Header bar
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + 20, 0xFF0F3460);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);

        // Question text (word-wrapped manually or with drawWordWrap)
        graphics.drawString(font, ">> " + currentQuestion.prompt(),
            leftPos + 10, topPos + 26, 0x00FF41); // green terminal color

        // Feedback line
        if (!feedback.isEmpty()) {
            graphics.drawString(font, feedback,
                leftPos + 10, topPos + 185, feedbackColor);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // Suppress default title rendering
    }
}