package com.peierlong.best.practices;

import java.util.EnumMap;
import java.util.Map;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/12/3 下午5:33
 * 描述 :  使用嵌套的EnumMap来构建关联的一对枚举(取代序数索引)
 */
public enum Phase {
    SOLED, LIQUID, GAS;

    public enum Transition {
        MELT(SOLED, LIQUID), FREEZE(LIQUID, SOLED),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLED, GAS), DEPOSIT(GAS, SOLED),;

        private final Phase src;
        private final Phase dst;

        Transition(Phase src, Phase dst) {
            this.src = src;
            this.dst = dst;
        }

        //初始化 Phase Transition Map
        private static final Map<Phase, Map<Phase, Transition>> m = new EnumMap<>(Phase.class);

        static {
            for (Phase p : Phase.values()) {
                m.put(p, new EnumMap<Phase, Transition>(Phase.class));
            }
            for (Transition trans : Transition.values()) {
                m.get(trans.src).put(trans.dst, trans);
            }
        }

        public static Transition from(Phase src, Phase dst) {
            return m.get(src).get(dst);
        }

    }

}
