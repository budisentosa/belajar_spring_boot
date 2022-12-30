package com.example.berzkoder02.controller;

import com.example.berzkoder02.dto.AppUserDto;
import com.example.berzkoder02.dto.ResponseData;
import com.example.berzkoder02.models.entities.AppUser;
import com.example.berzkoder02.services.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    // tambah @Valid
    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserDto dto) {
        ResponseData<AppUser> responseData = new ResponseData<>();
        AppUser user = modelMapper.map(dto, AppUser.class);
        responseData.setPayload(appUserService.registerAppUser(user));
        responseData.setStatus(true);
        return  ResponseEntity.ok().body(responseData);
    }
}
