package org.hanumoka.sample.account.application.port.in;

import org.hanumoka.sample.account.domain.Account;

public interface AccountCommandService {

    //1. Account 생성
    Long createAccount(Account domain);

    //2. Account 수정

    //3. Account 상태 변경

    //4. Account 권한 추가

    //5. Account 권한 삭제

    //3. Account 삭제
}
