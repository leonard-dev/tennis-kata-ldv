package fr.nexity.kata.tennis.services;

import com.google.common.collect.ImmutableMap;
import fr.nexity.kata.tennis.model.Player;
import fr.nexity.kata.tennis.model.set.PlayerSetScore;
import fr.nexity.kata.tennis.model.set.SetScore;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SetScoreServiceImpTest {

  private final SetScoreService setScoreService = new SetScoreServiceImpl();

  @Test
  public void shouldBeOtoOGivenInitialScore() {
    // WHEN
    SetScore initialSetScore = SetScore.INITIAL;
    // THEN
    Assertions.assertThat(initialSetScore).isSameAs(SetScore.INITIAL);
    Assertions.assertThat(initialSetScore.getPlayerSetScore(Player.PLAYER_1))
        .isEqualTo(new PlayerSetScore(0, false));
    Assertions.assertThat(initialSetScore.getPlayerSetScore(Player.PLAYER_2))
        .isEqualTo(new PlayerSetScore(0, false));
  }

  @Test
  public void shouldBe1to0WithoutWinIfPlayer1wins1game() {
    // GIVEN
    SetScore setScore = SetScore.INITIAL;
    // WHEN
    SetScore newSetScore = setScoreService.increment(setScore, Player.PLAYER_1);
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_1))
        .isEqualTo(new PlayerSetScore(1, false));
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_2))
        .isEqualTo(new PlayerSetScore(0, false));
  }

  @Test
  public void shouldBe5to4WithoutWinIfPlayer1winsLastGameFrom4to4() {
    // GIVEN
    SetScore setScore = new SetScore(
        ImmutableMap.of(
            Player.PLAYER_1, new PlayerSetScore(4, false),
            Player.PLAYER_2, new PlayerSetScore(4, false)));

    // WHEN
    SetScore newSetScore = setScoreService.increment(setScore, Player.PLAYER_1);
    Assertions.assertThat(newSetScore.hasWinner()).isFalse();
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_1))
        .isEqualTo(new PlayerSetScore(5, false));
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_2))
        .isEqualTo(new PlayerSetScore(4, false));
  }

  @Test
  public void shouldBe6to4WithWinIfPlayer1winsLastGameFrom5to4() {
    // GIVEN
    SetScore setScore = new SetScore(
        ImmutableMap.of(
            Player.PLAYER_1, new PlayerSetScore(5, false),
            Player.PLAYER_2, new PlayerSetScore(4, false)));

    // WHEN
    SetScore newSetScore = setScoreService.increment(setScore, Player.PLAYER_1);
    Assertions.assertThat(newSetScore.hasWinner()).isTrue();
    Assertions.assertThat(newSetScore.getWinner()).isEqualTo(Player.PLAYER_1);
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_1))
        .isEqualTo(new PlayerSetScore(6, true));
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_2))
        .isEqualTo(new PlayerSetScore(4, false));
  }

  @Test
  public void shouldBe6to5WithoutWinIfPlayer1winsLastGameFrom5to5() {
    // GIVEN
    SetScore setScore = new SetScore(
        ImmutableMap.of(
            Player.PLAYER_1, new PlayerSetScore(5, false),
            Player.PLAYER_2, new PlayerSetScore(5, false)));

    // WHEN
    SetScore newSetScore = setScoreService.increment(setScore, Player.PLAYER_1);
    Assertions.assertThat(newSetScore.hasWinner()).isFalse();
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_1))
        .isEqualTo(new PlayerSetScore(6, false));
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_2))
        .isEqualTo(new PlayerSetScore(5, false));
  }

  @Test
  public void shouldBe6to6WithoutWinIfPlayer1winsLastGameFrom5to6() {
    // GIVEN
    SetScore setScore = new SetScore(
        ImmutableMap.of(
            Player.PLAYER_1, new PlayerSetScore(5, false),
            Player.PLAYER_2, new PlayerSetScore(6, false)));

    // WHEN
    SetScore newSetScore = setScoreService.increment(setScore, Player.PLAYER_1);
    Assertions.assertThat(newSetScore.hasWinner()).isFalse();
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_1))
        .isEqualTo(new PlayerSetScore(6, false));
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_2))
        .isEqualTo(new PlayerSetScore(6, false));
  }

  @Test
  public void shouldBe7to5WithWinIfPlayer1winsLastGameFrom6to5() {
    // GIVEN
    SetScore setScore = new SetScore(
        ImmutableMap.of(
            Player.PLAYER_1, new PlayerSetScore(6, false),
            Player.PLAYER_2, new PlayerSetScore(5, false)));

    // WHEN
    SetScore newSetScore = setScoreService.increment(setScore, Player.PLAYER_1);
    Assertions.assertThat(newSetScore.hasWinner()).isTrue();
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_1))
        .isEqualTo(new PlayerSetScore(7, true));
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_2))
        .isEqualTo(new PlayerSetScore(5, false));
  }

  @Test
  public void shouldBe7to6WithWinIfPlayer1winsLastGameFrom6to6() {
    // GIVEN
    SetScore setScore = new SetScore(
        ImmutableMap.of(
            Player.PLAYER_1, new PlayerSetScore(6, false),
            Player.PLAYER_2, new PlayerSetScore(6, false)));

    // WHEN
    SetScore newSetScore = setScoreService.increment(setScore, Player.PLAYER_1);
    Assertions.assertThat(newSetScore.hasWinner()).isTrue();
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_1))
        .isEqualTo(new PlayerSetScore(7, true));
    Assertions.assertThat(newSetScore.getPlayerSetScore(Player.PLAYER_2))
        .isEqualTo(new PlayerSetScore(6, false));
  }

}
