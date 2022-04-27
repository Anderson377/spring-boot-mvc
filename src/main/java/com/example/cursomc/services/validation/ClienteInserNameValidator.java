package com.example.cursomc.services.validation;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.enuns.TipoCliente;
import com.example.cursomc.dto.ClienteNewDTO;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.resources.exception.FieldMessage;
import com.example.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInserNameValidator {


    public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

        @Autowired
        private ClienteRepository repo;

        @Override
        public void initialize(ClienteInsert ann) {
        }

        @Override
        public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
            List<FieldMessage> list = new ArrayList<>();

            if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
                list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
            }
            if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
                list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
            }

            Cliente aux = repo.findByEmail(objDto.getEmail());
            if(aux != null){
                list.add(new FieldMessage("email", "E-mail já existente"));
            }

            // inclua os testes aqui, inserindo erros na lista
            for (FieldMessage e : list) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(e.getMessage())
                        .addPropertyNode(e.getFieldName()).addConstraintViolation();
            }
            return list.isEmpty();
        }
    }
}
