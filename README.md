# miacc_youth_2022_summer_retreat
미아중앙교회 2022 청년/학생 연합 여름 수련회 팀별 미션 진행을 위해 개발한 WebApp 입니다. Spring framework를 사용했습니다.

## 설명

### 1) 미션 진행
참가자들은 4개의 팀으로 나뉘어져, 각자의 팀마다 서로 다른 미션을 수행하게 됩니다.

본 WebApp을 통해 미션의 내용을 전달 받고, 미션을 해결해서 알아낸 비밀번호를 웹페이지에 입력하면 다음 미션으로 넘어가게 됩니다.

비밀번호 입력 외에도 파일 업로드 기능을 통해 인증샷을 업로드하는 기능도 있습니다.

<p align="center">
  <img height="500" alt="image" src="https://user-images.githubusercontent.com/16686954/197393524-17842ad3-b19f-4faa-963b-d764915b7f14.jpeg">
  <img height="500" alt="image" src="https://user-images.githubusercontent.com/16686954/197394312-67a56f20-e963-441d-9225-91562df71b92.jpeg">
  <img height="500" alt="image" src="https://user-images.githubusercontent.com/16686954/197394393-23273953-6122-4981-96c9-ccd81d02d21c.jpeg">
</p>

### 2) 모니터링
각 팀의 미션 진행 현황이 슬랙 채널로 전달됩니다.

특정 미션에서 막히거나, 표기법 문제 등으로 인해 반복적으로 틀린 비밀번호를 입력하는 경우 등, 참가자들이 답답함을 느낄 수 있는 상황을 사전에 차단하기 위해서 슬랙을 통한 모니터링 기능을 구현하였습니다.

참가자들이 사진을 업로드하는 경우 해당 사진에 대한 URL이 슬랙 채널로 전달되기 때문에 서버 외부에서도 쉽게 사진을 확인할 수 있습니다.

<img width="1665" alt="스크린샷 2022-10-23 오후 10 01 40" src="https://user-images.githubusercontent.com/16686954/197393651-416f6df7-b971-44a8-9fe2-7dca37f5e675.png">

## 회고록
[MIACC 2022 학생/청년 여름 수련회 웹페이지 - 회고록](https://kloong.tistory.com/entry/MIACC-2022-%ED%95%99%EC%83%9D%EC%B2%AD%EB%85%84-%EC%97%AC%EB%A6%84-%EC%88%98%EB%A0%A8%ED%9A%8C-%EC%9B%B9%ED%8E%98%EC%9D%B4%EC%A7%80-%ED%9A%8C%EA%B3%A0%EB%A1%9D?category=1291681)
