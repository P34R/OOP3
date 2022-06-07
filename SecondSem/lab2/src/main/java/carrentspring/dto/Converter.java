package carrentspring.dto;


public interface Converter<M, D> {

  D toDto(M model);

  M toModel(D dto);
}
