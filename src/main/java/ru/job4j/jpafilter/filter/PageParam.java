package ru.job4j.jpafilter.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {

    @Schema(description = "Номер страницы, начиная с 0", example = "1")
    private Integer pageNumber;

    @Schema(description = "Количество записей на странице", example = "12")
    private Integer pageSize;

    @Schema(description = "Наименование поля, по которому будет сортировка", example = "inn")
    private String sortColumn;

    @Schema(description = "Направление сортировки, asc - по возрастанию, иное значение или null будет означать desc - по убыванию")
    private String sortDirection;
}
