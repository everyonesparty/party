package everyonesparty.party.presentation.dto.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import everyonesparty.party.usecase.domain.enums.codevalue.CodeValue;

import java.io.IOException;

/***
 * > CodeValue 인터페이스는 기본적으로 name 값이 직렬화에 사용됨
 * > CodeValueSerializer 를 적용하면 value 값이 직렬화에 사용되도록 수정 가능
 * > 참고: https://multifrontgarden.tistory.com/172
 */
public class CodeValueSerializer extends JsonSerializer<CodeValue> {

    @Override
    public void serialize(CodeValue codeValue, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeString(codeValue.getValue());
    }
}
