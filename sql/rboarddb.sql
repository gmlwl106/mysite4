--계층형 게시판 (rboard)

--rboard 테일블 삭제
drop table rboard;
--rboard 시퀀스 삭제
drop sequence seq_rboard_no;

--rboard 테이블 생성
create table rboard (
    no  number,
    user_no number  not null,
    title   varchar2(500),
    content varchar2(4000),
    hit number,
    reg_date    date,
    group_no    number,
    order_no    number,
    depth   number,
    primary key(no),
    constraint userR_fk foreign key (user_no)
    references users(no)
);

--rboard 시퀀스 생성
create sequence seq_rboard_no
increment by 1
start with 1
nocache;

--rboard 출력
select  no
        ,user_no
        ,title
        ,content
        ,hit
        ,to_char(reg_date,'YY-MM-DD HH24:MI') reg_date
        ,group_no
        ,order_no
        ,depth
from rboard;


--기본 글 추가
insert into rboard
values(seq_rboard_no.nextval, 2, '1번글 제목입니다', '1번글 내용입니다', 0, sysdate, 1, 1, 0);
insert into rboard
values(seq_rboard_no.nextval, 2, '2번글 제목입니다', '2번글 내용입니다', 0, sysdate, 2, 1, 0);
insert into rboard
values(seq_rboard_no.nextval, 2, '3번글 제목입니다', '3번글 내용입니다', 0, sysdate, 3, 1, 0);
insert into rboard
values(seq_rboard_no.nextval, 2, '4번글 제목입니다', '4번글 내용입니다', 0, sysdate, 4, 1, 0);

commit;
rollback;

