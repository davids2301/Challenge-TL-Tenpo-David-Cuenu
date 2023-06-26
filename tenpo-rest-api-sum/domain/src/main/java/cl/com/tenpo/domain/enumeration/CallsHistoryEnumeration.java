package cl.com.tenpo.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CallsHistoryEnumeration {

    ID("id"), REQUEST("request"), RESPONSE("response"), DATE_AND_HOUR("dateAndHour");

    private String value;

}
