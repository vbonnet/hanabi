package com.hanabi.ui.viewer;

import com.hanabi.com.hanabi.deducer.CardInference;
import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.impl.GameEngine;
import com.hanabi.model.impl.Hand;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.IntStream;


public class HanabiViewer extends Application {
  enum Cardinal {
    LEFT, RIGHT, TOP, BOTTOM
  }

  final Font INFERENCE_FONT = Font.font("Monospaced", FontWeight.BOLD, 16);
  final Font CARD_FONT = Font.font("Monospaced", FontWeight.BOLD, 25);

  final int CARD_WIDTH = 85;
  final int CARD_HEIGHT = 130;

  BorderPane root = new BorderPane();
  GameEngine engine;
  HumanPlayer player = new HumanPlayer();
  List<HumanPlayer> players = new ArrayList<>();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Hanabi Rocks!");

    IntStream.range(0, 4).forEach(i -> players.add(new HumanPlayer()));
    player = players.get(0);

    engine = new GameEngine(new ArrayList<>(players));
    engine.initialize();

    root.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    root.setPadding(new Insets(15, 15, 15, 15));

    root.setLeft(drawPlayerHandWithInference(players.get(0), Cardinal.LEFT));
    root.setRight(drawPlayerHandWithInference(players.get(1), Cardinal.RIGHT));
    root.setTop(drawPlayerHandWithInference(players.get(2), Cardinal.TOP));
    root.setBottom(drawPlayerHandWithInference(players.get(3), Cardinal.BOTTOM));

    primaryStage.setScene(new Scene(root, 700, 800));
    primaryStage.show();
  }

  private Pane drawPlayerHandWithInference(HumanPlayer player, Cardinal cardinal) {
    List<Pair<Card, CardInference>> hand = new ArrayList<>();
    Hand playerHand = engine.getState().getPlayerHand(player);
    for (CardPlaceholder placeholder : playerHand.getPlaceholders()) {
      hand.add(new Pair<>(
          playerHand.getCard(placeholder),
          player.handInference.hand.get(placeholder)));
    }

    Pane box = orientedHorizontalBox(cardinal, 20);

    addSpacer(box);
    for (Pair<Card, CardInference> entry : hand) {
      Pane cardPane = drawCard(entry.getKey(), entry.getValue(), cardinal);
      box.getChildren().add(cardPane);
    }
    addSpacer(box);

    BorderPane.setAlignment(box, Pos.CENTER);
    return box;
  }

  private Pane drawCard(Card card, CardInference inference, Cardinal cardinal) {
    int spacing = 1;

    Pane box = orientedHorizontalBox(cardinal, spacing);
    switch (cardinal) {
      case LEFT:
      case RIGHT:
        box.setMinHeight(CARD_WIDTH);
        box.setMinWidth(CARD_HEIGHT);
        break;
      case TOP:
      case BOTTOM:
        box.setMinHeight(CARD_HEIGHT);
        box.setMinWidth(CARD_WIDTH);
        break;
    }

    box.setPadding(new Insets(5, 5, 5, 5));
    BackgroundFill backgroundFill = new BackgroundFill(colorToFill(card.getColor()), new CornerRadii(10), Insets.EMPTY);
    box.setBackground(new Background(backgroundFill));

    if (inference != null) {
      // List all possible numbers
      Pane numbersPane = orientedVerticalBox(cardinal, 3);
      Set<Integer> possibleNumbers = inference.getPossibleNumbers();
      IntStream.rangeClosed(1, 5).forEach(i -> {
        if (possibleNumbers.contains(i)) {
          addInferenceLabel(numbersPane, Integer.toString(i), cardinal);
        } else {
          addEmptyLabel(numbersPane);
        }
        addSpacer(numbersPane);
      });
      box.getChildren().add(numbersPane);
    }

    addSpacer(box);

    // Show the real value of the card
    Pane valuePane = orientedVerticalBox(cardinal, spacing);
    addSpacer(valuePane);
    addCardLabel(valuePane, card, cardinal);
    addSpacer(valuePane);
    box.getChildren().add(valuePane);

    addSpacer(box);

    // List all possible colors
    if (inference != null) {
      Pane colorsPane = orientedVerticalBox(cardinal, spacing);
      Set<CardColor> possibleColors = inference.getPossibleColors();
      for (CardColor color : CardColor.values()) {
        if (possibleColors.contains(color)) {
          addInferenceLabel(colorsPane, colorToLetter(color), cardinal);
        } else {
          addEmptyLabel(colorsPane);
        }
        addSpacer(colorsPane);
      }
      box.getChildren().add(colorsPane);
    }

    TilePane.setAlignment(box, Pos.CENTER);
    return box;
  }

  private void rotate(Label pane, Cardinal cardinal) {
    switch (cardinal) {
      case LEFT:
        pane.setRotate(270);
        break;
      case RIGHT:
        pane.setRotate(90);
        break;
    }
  }

  private Pane orientedHorizontalBox(Cardinal cardinal, int spacing) {
    switch (cardinal) {
      case TOP:
      case BOTTOM:
        HBox hbox = new HBox();
        hbox.setSpacing(spacing);
        return hbox;
      case LEFT:
      case RIGHT:
        VBox vbox = new VBox();
        vbox.setSpacing(spacing);
        return vbox;
    }
    return null;
  }

  private Pane orientedVerticalBox(Cardinal cardinal, int spacing) {
    switch (cardinal) {
      case TOP:
      case BOTTOM:
        VBox vbox = new VBox();
        vbox.setSpacing(spacing);
        return vbox;
      case LEFT:
      case RIGHT:
        HBox hbox = new HBox();
        hbox.setSpacing(spacing);
        return hbox;
    }
    return null;
  }

  private void addSpacer(Pane pane) {
    Region region = new Region();
    //  region.setStyle("-fx-background-color: red; -fx-background-radius: 10 10 10 10;");
    VBox.setVgrow(region, Priority.ALWAYS);
    HBox.setHgrow(region, Priority.ALWAYS);
    pane.getChildren().add(region);
  }

  private void addCardLabel(Pane pane, Card card, Cardinal cardinal) {
    Label label = new Label();
    label.setFont(CARD_FONT);
    label.setText(Integer.toString(card.getNumber()));
    pane.getChildren().add(label);
    rotate(label, cardinal);
  }

  private void addInferenceLabel(Pane pane, String inference, Cardinal cardinal) {
    Label label = new Label();
    label.setFont(INFERENCE_FONT);
    label.setText(inference);
    pane.getChildren().add(label);
    rotate(label, cardinal);
  }

  private void addEmptyLabel(Pane pane) {
    Label label = new Label();
    label.setText(" ");
    pane.getChildren().add(label);
  }

  private String  colorToLetter(CardColor color) {
    switch (color) {
      case RED:
        return "R";
      case BLUE:
        return "B";
      case GREEN:
        return "G";
      case WHITE:
        return "W";
      case YELLOW:
        return "Y";
    }
    return "";
  }

  private Color colorToFill(CardColor color) {
    switch (color) {
      case RED:
        return Color.RED;
      case BLUE:
        return Color.BLUE;
      case GREEN:
        return Color.GREEN;
      case WHITE:
        return Color.WHITE;
      case YELLOW:
        return Color.YELLOW;
    }
    return Color.BLACK;
  }

}
