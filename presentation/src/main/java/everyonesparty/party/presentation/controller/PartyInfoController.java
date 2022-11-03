package everyonesparty.party.presentation.controller;

import everyonesparty.party.presentation.dto.CurrentUserPartyInfoDTO;
import everyonesparty.party.presentation.dto.SaveOrganizerInfoDTO;
import everyonesparty.party.presentation.response.ResponseUtils;
import everyonesparty.party.usecase.service.PartyInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


/***
 * > 파티 정보 CRUD 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PartyInfoController {

    private final PartyInfoService partyInfoService;

    @ApiOperation(value = "로그인한 사용자의 파티 현황 정보 조회", notes = "https://keen-derby-c16.notion.site/104fdfb176d44330b59388950776d611")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "카카오 로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/user/party")
    public ResponseEntity<CurrentUserPartyInfoDTO> findPartyInfoByUserId(@ApiIgnore String kakaoIdFromToken) {
        return ResponseUtils.out(CurrentUserPartyInfoDTO.Res.fromDomian(partyInfoService.findMatchingPartyInfoByKakaoId(kakaoIdFromToken)));
    }

    @ApiOperation(value = "파티장 정보 등록", notes = "https://keen-derby-c16.notion.site/e7d3e30ca84e4eb78177d10ba79a1087")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "카카오 로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/party/organizer")
    public ResponseEntity<?> saveOrganizerInfo(@ApiIgnore String kakaoIdFromToken, @Valid @RequestBody SaveOrganizerInfoDTO.Req savePartyInfoDTO) {
        return ResponseUtils.out(SaveOrganizerInfoDTO.Res.fromDomian(partyInfoService.saveOrganizerInfo(savePartyInfoDTO.toDomain())));
    }
}
