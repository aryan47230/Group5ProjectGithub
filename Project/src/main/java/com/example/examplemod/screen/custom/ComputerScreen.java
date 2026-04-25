package com.example.examplemod.screen.custom;

import com.example.examplemod.block.custom.AnswerPacket;
import com.example.examplemod.block.custom.QuestionBank;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class ComputerScreen extends AbstractContainerScreen<ComputerMenu> {

    private QuestionBank.Question currentQuestion;
    private int currentCorrectIndex;
    private String feedback = "";
    private int feedbackColor = 0xFFFFFFFF;

    private int selectedIndex = -1;  // -1 means nothing chosen yet
    private Button submitButton;

    private int correctCount = 0;

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

        for (int i = 0; i < currentQuestion.choices().length; i++) {
            final int idx = i;
            addRenderableWidget(Button.builder(
                Component.literal((char)('A' + i) + ") " + currentQuestion.choices()[i]),
                btn -> onChoiceSelected(idx)
            ).bounds(x + 10, y + 60 + i * 24, 236, 20).build());
        }

        submitButton = addRenderableWidget(Button.builder(
            Component.literal("Submit"),
            btn -> onSubmit()
        ).bounds(x + 88, y + 160, 80, 20).build());

        submitButton.visible = false;
    }

    private void onChoiceSelected(int idx) {
        selectedIndex = idx;
        submitButton.visible = true;
        feedback = "";  // clear any old feedback
    }

    private void onSubmit() {
        if (selectedIndex == -1) return;

        renderables.forEach(w -> {
            if (w instanceof Button b && b != submitButton) b.active = false;
        });
        submitButton.active = false;

        if (selectedIndex == currentCorrectIndex) {
            correctCount++;
            if (correctCount >= 3) {
                feedback = "Correct! All 3 answered - item awarded!";
                feedbackColor = 0xFF55FF55;
                ClientPacketDistributor.sendToServer(new AnswerPacket(selectedIndex));
            } else {
                feedback = "Correct! " + correctCount + "/3 - next question...";
                feedbackColor = 0xFF55FF55;
                // Auto-advance to next question after a short delay
                Minecraft.getInstance().execute(() -> scheduleNextQuestion());
            }
        } else {
            correctCount = 0;  // reset on wrong answer
            feedback = "Wrong! Progress reset. Try again...";
                feedbackColor = 0xFFFF5555;
                // Auto-advance to next question after a short delay
                Minecraft.getInstance().execute(() -> scheduleNextQuestion());
        }
    }

    private void scheduleNextQuestion() {
        // Wait ~60 ticks (3 seconds) then load next question
        new Thread(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            Minecraft.getInstance().execute(() -> {
                loadNewQuestion();
                selectedIndex = -1;
                clearWidgets();
                init();
            });
        }).start();
    }

    private void drawWrappedString(GuiGraphics graphics, String text, int x, int y, int color) {
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            graphics.drawString(font, lines[i], x, y + (i * 10), color);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF1A1A2E);
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + 20, 0xFF0F3460);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);

        drawWrappedString(graphics, ">> " + currentQuestion.prompt(),
        leftPos + 10, topPos + 26, 0xFFFFFFFF);

        if (!feedback.isEmpty()) {
            graphics.drawString(font, feedback,
                leftPos + 10, topPos + 185, feedbackColor);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // suppress default title rendering
    }
}
