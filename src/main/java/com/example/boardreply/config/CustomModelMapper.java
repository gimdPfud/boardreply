package com.example.boardreply.config;

import org.modelmapper.ModelMapper;

public class CustomModelMapper extends ModelMapper {
    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        if(source==null){
            return null;
        }
        else {
            return super.map(source, destinationType);
        }
    }
}
