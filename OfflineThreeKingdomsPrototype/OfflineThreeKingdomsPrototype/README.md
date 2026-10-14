# 삼국 오프라인 전투 · 첫 번째 안드로이드 프로토타입

## 실제 분석 결과
- 업로드된 ZIP은 AndroidManifest.xml, classes.dex, libcocos2djs.so를 포함하는 **APK 형식의 패키지**입니다.
- Cocos2d-x / Cocos Creator 계열 실행 환경과 게임 리소스가 포함되어 있습니다.
- `assets/assets/resources/config.3883b.json`의 경로 목록에 영웅 및 스킬 테이블 관련 항목이 있습니다. `analysis.json` 참조.
- 실제 오프라인 실행 여부, 서버 로그인/전투 의존성, 원본 스킬 수치의 추출은 아직 검증되지 않았습니다.

## 이번 구현물
- 네트워크 권한 없는 독립형 Android 앱
- 한 명의 아군과 적의 턴제 전투, 일반 공격, 예시 스킬, 승패, 재시작
- 전투 수치는 **임시값**입니다. 원본 게임의 스킬이나 동작을 정확히 복원한 것이 아닙니다.
- 원본의 저작권 보호 이미지, 음원, 소스 코드는 포함하지 않았습니다.

## 실행 방법
1. Android Studio에서 이 폴더를 열고 Gradle 동기화합니다.
2. 안드로이드 기기를 연결하거나 에뮬레이터를 켭니다.
3. Run app을 누르거나 `gradle assembleDebug`로 APK를 빌드합니다.
4. 생성된 `app/build/outputs/apk/debug/app-debug.apk`를 설치합니다.

이 환경에는 Android SDK/Gradle이 없어 실제 APK 빌드 및 기기 실행 테스트는 수행하지 못했습니다.

## 다음 작업
- 원본 APK 실행 여부 검증 및 서버 호출 경로 확인
- 리소스 테이블 UUID 매핑과 실제 데이터 파싱
- 전투 규칙 및 캐릭터 화면 구현
- 사용 권한이 확보된 이미지와 음원만 추가
