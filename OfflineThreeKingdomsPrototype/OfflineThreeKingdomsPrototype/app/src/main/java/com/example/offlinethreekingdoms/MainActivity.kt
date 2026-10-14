package com.example.offlinethreekingdoms

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.math.max

/** Standalone original prototype. No network, copyrighted art, or extracted game code. */
class MainActivity : Activity() {
    private var heroHp = 1000
    private var enemyHp = 1600
    private var turn = 0
    private lateinit var status: TextView
    private lateinit var log: TextView
    private lateinit var attack: Button
    private lateinit var skill: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(36, 64, 36, 32); setBackgroundColor(Color.rgb(22, 27, 44)) }
        fun label(text: String, size: Float): TextView = TextView(this).apply { this.text = text; textSize = size; setTextColor(Color.WHITE); gravity = Gravity.CENTER; setPadding(0, 18, 0, 18) }
        root.addView(label("삼국 오프라인 전투 · 프로토타입", 23f))
        status = label("", 20f); root.addView(status)
        log = label("일반 공격 또는 스킬을 선택하세요.", 16f); root.addView(log)
        attack = Button(this).apply { text = "일반 공격"; setOnClickListener { hit(180, "일반 공격") } }; root.addView(attack)
        skill = Button(this).apply { text = "은침 (예시 스킬 · 3턴마다 사용)"; setOnClickListener { if (turn % 3 == 0) hit(420, "은침") else log.text = "스킬 대기 중: ${3 - turn % 3}턴" } }; root.addView(skill)
        root.addView(Button(this).apply { text = "전투 다시 시작"; setOnClickListener { heroHp = 1000; enemyHp = 1600; turn = 0; log.text = "전투를 다시 시작했습니다."; update() } })
        root.addView(label("※ 피해량과 스킬 규칙은 임시 테스트 값이며 원본 게임 수치가 아닙니다.", 12f))
        setContentView(root); update()
    }
    private fun hit(damage: Int, name: String) {
        if (heroHp <= 0 || enemyHp <= 0) return
        enemyHp = max(0, enemyHp - damage)
        if (enemyHp > 0) heroHp = max(0, heroHp - 110)
        turn++
        log.text = when { enemyHp == 0 -> "$name! 적을 쓰러뜨렸습니다."; heroHp == 0 -> "패배했습니다."; else -> "$name: 적에게 $damage 피해, 반격 110 피해" }
        update()
    }
    private fun update() {
        status.text = "아군 HP: $heroHp / 1000\n적군 HP: $enemyHp / 1600\n진행 턴: $turn"
        attack.isEnabled = heroHp > 0 && enemyHp > 0
        skill.isEnabled = attack.isEnabled
    }
}
