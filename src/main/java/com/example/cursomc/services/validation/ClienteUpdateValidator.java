package com.example.cursomc.services.validation;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.enuns.TipoCliente;
import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.dto.ClienteNewDTO;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.resources.exception.FieldMessage;
import com.example.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator {


    public class ClienteInsertValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

        @Autowired
        private HttpServletRequest request;

        @Autowired
        private ClienteRepository repo;

        @Override
        public void initialize(ClienteUpdate ann) {
        }

        @Override
        public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

            // Para pegar o numeor do ID que foi passado na URI
            Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            Integer uriId = Integer.parseInt(map.get("id"));

            List<FieldMessage> list = new ArrayList<>();

            Cliente aux = repo.findByEmail(objDto.getEmail());
            if(aux != null && !aux.getId().equals(uriId)){
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
