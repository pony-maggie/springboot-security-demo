package com.lucas.token.tokendemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucas.token.tokendemo.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}
