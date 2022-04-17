### 객체 모델
<img src="/docs/model.png" width="70%" height="auto" >


### 기능 구현 목록

- StrikeZoneFactory
    - 각 자리가 1~9로 구성된 세 자리 수(스트라이크 존) 생성 
        - 생성된 스트라이크 존에 대한 유효성 검사
        - 유효성 검사가 통과된 숫자에 대해 저장
      
- PitcherRule
    - 입력받은 문자열에 대해 유효성 검사
    - 유효성 결과에 대해 참, 거짓 반환
  
- Referee
    - StrikeZoneFactory로부터 생성된 strikeZone을 저장
    - Pitcher의 pitchNumber와 strikeZone을 비교 및 결과 반환

- Pitcher
    - 사용자로부터 pitchNumber를 입력 받음
    - PitcherRule로부터 pitchNumber 검증
      - 유효하지 않은 결과에 대해 IleegalArgumentException 발생
    
- BaseballGame
    - ScoreBoard에 점수 전달
    - 3스트라이크 : 사용자로부터 게임 재시작, 중지 여부 확인
      - 유효하지 않은 입력 시 IllegalArgumentException 발생
    - other : 기존 게임 지속

- ScoreBoard
  - 전달 받은 점수를 출력 형태로 가공
  - 가공된 점수를 출력
