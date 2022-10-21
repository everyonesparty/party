package everyonesparty.party.presentation.controller;

import everyonesparty.party.presentation.dto.CurrentOttStatusDTO;
import everyonesparty.party.presentation.response.ResponseUtils;
import everyonesparty.party.usecase.service.CurrentOttStatusService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/api/v1/ott/status")
@RequiredArgsConstructor
public class CurrentOttStatusController {

    private final CurrentOttStatusService currentOttStatusService;

    @ApiOperation(value = "ott 상태 조회", notes = "작성예정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "카카오 로그인 성공 후 jwt token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<List<CurrentOttStatusDTO.Res>> findAll() {
        return ResponseUtils.out(currentOttStatusService.findAll().stream()
                .map(CurrentOttStatusDTO.Res::fromDomian)
                .collect(Collectors.toList()));
    }
}
