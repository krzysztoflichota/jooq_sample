package com.klichota.jooqdemo.domain.param;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
abstract class AbstractParam<T> {

    protected final String code;

    public abstract T getValue();
}
