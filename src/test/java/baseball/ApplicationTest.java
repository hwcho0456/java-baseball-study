package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

  @Test
  void 입력값_검증_예외_테스트1() {
    assertSimpleTest(() ->
        assertThatThrownBy(() -> runException("0"))
            .isInstanceOf(IllegalArgumentException.class)
    );
  }

  @Test
  void 입력값_검증_예외_테스트2() {
    assertSimpleTest(() ->
        assertThatThrownBy(() -> runException("3"))
            .isInstanceOf(IllegalArgumentException.class)
    );
  }

  @Test
  void 게임진행_결과_출력_테스트() {
    assertRandomNumberInRangeTest(
        () -> {
          run("246", "657", "567", "2");
          assertThat(output()).contains("1볼", "2볼 1스트라이크", "3스트라이크", "게임 종료");
        },
        5, 6, 7
    );
  }

  @Test
  void 게임종료_후_재시작() {
    assertRandomNumberInRangeTest(
        () -> {
          run("246", "135", "1", "597", "589", "2");
          assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
        },
        1, 3, 5, 5, 8, 9
    );
  }

  @Test
  void 예외_테스트() {
    assertSimpleTest(() ->
        assertThatThrownBy(() -> runException("1234"))
            .isInstanceOf(IllegalArgumentException.class)
    );
  }

  @Test
  void 옵션_예외_테스트() {
    assertSimpleTest(() ->
        assertThatThrownBy(() -> runException("123", "aa"))
            .isInstanceOf(IllegalArgumentException.class)
    );
  }

  @Override
  public void runMain() {
    Application.main(new String[]{});
  }
}
