package kz.kbtu.algoapp.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    @NonNull
    @NotBlank
    private String username;

    @NonNull
    @NotBlank
    private String email;

    @NotNull(message = "password is not provided or invalid")
    @NotBlank(message = "password is not provided or invalid")
    private String password;

    @NotNull(message = "password-confirm is not provided or invalid")
    @NotBlank(message = "password-confirm is not provided or invalid")
    @JsonProperty("password-confirm")
    private String passwordConfirm;

    private String roles;
}
