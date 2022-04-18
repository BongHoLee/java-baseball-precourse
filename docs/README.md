## 기능 구현 목록

### 컨트롤러
- BaseballGame
    - Referee에게 게임 시작 메시지 전송
    - 한 게임 끝날 때 마다 Display에 결과 점수 전달  
    - 3스트라이크 : 사용자로부터 게임 재시작, 중지 여부 확인
        - 1 입력 시 새로운 게임 시작
        - 2 입력 시 게임 중지  
        - 유효하지 않은 입력에 대해 예외 발생 및 종료
    - other : 기존 게임 지속

### 모델 
- StrikeZoneFactory
    - 각 자리가 1~9로 구성된 세 자리 수(스트라이크 존) 생성 
        - 생성된 스트라이크 존에 대한 유효성 검사
        - 유효성 검사가 통과된 숫자에 대해 int형 배열로 변환 및 반환
      
- PitcherRule
    - 입력받은 문자열에 대해 유효성 검사
    - 유효성 결과에 대해 예외 발생
  
- Referee
    - StrikeZoneFactory로부터 생성된 strikeZone을 저장
    - Pitcher의 pitchNumber와 strikeZone을 비교 및 결과 반환

- Pitcher
    - 사용자로부터 pitchNumber를 입력 받음
    - 검증된 pitcherNumber를 int형 배열로 변환 및 반환
    
### VIEW
- ScoreBoard
    - 전달 받은 점수를 출력 형태로 가공

- Display
    - 전달 받은 결과 점수 출력
    - pitcherNumber 입력 요청 메시지 출력
    - 게임 종료 및 재시작을 위한 입력 요청 메시지 출력