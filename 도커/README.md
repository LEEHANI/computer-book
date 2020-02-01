## 도커


## 명령어

- 이미지 검색하기 `docker search mysql`
- 이미지 받기 `docker pull [이미지이름]:[태그]`
- 이미지 삭제하기 `docker rmi [이미지이름]:[태그]`
- 이미지 목록 출력하기 `docker images`

- 컨테이너 출력하기 `docker ps`
  + `-a` 정지된 컨테이너까지 출력
- 컨테이너 생성하기 `docker run -i -t --name mysql mysql bash`
- 컨데이터 시작하기 `docker start mysql`
- 컨테이너 재시작하기 `docker restart mysql`
- 컨테이너 접속하기 `docker attach mysql` 
- 컨테이너 정지하기 `docker stop`
- 컨테이너 삭제하기 `docker rm mysql`
- 외부에서 컨테이너 내부에 명령하기 `docker exec [컨테이너이름] [명령] [매개변수]`


