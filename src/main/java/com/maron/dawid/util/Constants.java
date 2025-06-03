package com.maron.dawid.util;

public class Constants {

    public enum Direction {
        LEFT,
        UP,
        RIGHT,
        DOWN
    }

    public static class PlayerConstants {

        public enum PlayerAction {
            IDLE(0, 2),
            IDLE_2(1, 2),
            WALK(2, 4),
            RUN(3, 8),
            FALL_RECOVERY(4, 6),
            JUMP(5, 8),
            DISAPPEAR(6, 4),
            DEATH(7, 8),
            ATTACK(8, 8),
            ;

            private final int actionIndex;
            private final int animationsNumber;


            public int getAnimationsNumber() {
                return animationsNumber;
            }

            public int getActionIndex() {
                return actionIndex;
            }

            public static PlayerAction getAnimationByActionIndex(int index) {
                for (PlayerAction playerAction : PlayerAction.values()) {
                    if (index == playerAction.getActionIndex()) {
                        return playerAction;
                    }
                }
                throw new UnsupportedOperationException();
            }

            PlayerAction(int playerAction, int animationsNumber) {
                actionIndex = playerAction;
                this.animationsNumber = animationsNumber;
            }
        }
    }
}
