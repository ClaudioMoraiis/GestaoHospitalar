package com.example.demo.paciente.service;

import com.example.demo.jwt.TokenService;
import com.example.demo.paciente.dto.PacienteCadastroDTO;
import com.example.demo.paciente.repository.PacienteRepository;
import com.example.demo.paciente.vo.PacienteVO;
import com.example.demo.util.ResponseApiUtil;
import com.example.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository fRepository;

    @Autowired
    private PasswordEncoder fPasswordEncoder;

    @Autowired
    private AuthenticationManager fAuthenticationManager;

    @Autowired
    private TokenService fTokenService;

    public ResponseEntity<?> validar(PacienteCadastroDTO mDTO) {
        String mCpf = Util.formatarCPF(mDTO.getCpf());
        if ((mCpf.length() != 14) || (mCpf.matches(".*\\p{L}.*"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseApiUtil.response("Erro", "Documento informado não é válido.")
            );
        }

        LocalDate mDataNascimento = Util.formatarData(mDTO.getDataNascimento().toString());
        if (mDataNascimento == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseApiUtil.response("Erro", "Formato da data inválida, informe no padrão" +
                            " dd-mm-yyyy")
            );
        }

        if (mDataNascimento.isAfter(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseApiUtil.response("Erro", "Data de nascimento inválida")
            );
        }

        if (fRepository.findByCpf(mDTO.getCpf()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    ResponseApiUtil.response("Erro", "Já existe cadastro com esse CPF.")
            );
        }
        ;

        if (fRepository.findByEmail(mDTO.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    ResponseApiUtil.response("Erro", "Já existe cadastro com esse e-mail.")
            );
        }

        if ((!mDTO.getSexo().toUpperCase().equals("F")) && (!mDTO.getSexo().toUpperCase().equals("M")) && (!mDTO.getSexo().toUpperCase().equals("OUTRO"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseApiUtil.response("Erro", "No campo sexo preencha apenas 'F, M ou OUTRO'")
            );
        }

        return null;
    }

    public ResponseEntity<?> cadastrar(PacienteCadastroDTO mDTO) {
        ResponseEntity mValidacao = validar(mDTO);
        if (mValidacao != null) {
            return mValidacao;
        }

        LocalDate mDataNascimento = Util.formatarData(mDTO.getDataNascimento().toString());
        String mSenhaCriptografada = fPasswordEncoder.encode(mDTO.getSenha());
        PacienteVO mPacienteVO = new PacienteVO();
        mPacienteVO.setSexo(mDTO.getSexo());
        mPacienteVO.setCpf(mDTO.getCpf());
        mPacienteVO.setDataNascimento(mDataNascimento);
        mPacienteVO.setNome(mDTO.getNome());
        mPacienteVO.setEmail(mDTO.getEmail());
        mPacienteVO.setSenha(mSenhaCriptografada);
        try {
            fRepository.save(mPacienteVO);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseApiUtil.response("Sucesso", "Paciente cadastrado com sucesso")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseApiUtil.response("Erro", "Erro ao cadastrar paciente" + e.getMessage())
            );
        }
    }

    public ResponseEntity<?> login(String mEmail, String mSenha) {
        if (mEmail.isEmpty() || mSenha.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseApiUtil.response("Erro", "E-mail ou senha incorreto")
            );
        }

        PacienteVO mPacienteVO = fRepository.findByEmail(mEmail);
        if (mPacienteVO == null || !fPasswordEncoder.matches(mSenha, mPacienteVO.getSenha())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseApiUtil.response("Erro", "E-mail ou senha incorreto")
            );
        }

        var mToken = fTokenService.generateToken(mPacienteVO);

        return ResponseEntity.ok(
                ResponseApiUtil.response("Sucesso", "Login realizado com sucesso\nToken: " + mToken)
        );


}
}
