package com.springapp.spring_api.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springapp.spring_api.AppUser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
  
  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request, 
    @NonNull HttpServletResponse response, 
    @NonNull FilterChain filterChain) throws ServletException, IOException {
    log.info("============> Starting filtering .....");
    String header = request.getHeader(SecurityConstants.HEADER_STRING);
    if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
        filterChain.doFilter(request, response);
        return;
    }
    String token = header.replace(SecurityConstants.TOKEN_PREFIX, "");
    log.info(token);
    /*
    try {
            AppUser appUser = this.validateCamdigikeyToken(token);

            if (!ObjectUtils.isEmpty(appUser)) {
                log.info("appUser ====> {}", appUser.getAuthorities());

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(appUser, "", appUser.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("============> Valid Token");
            }else {
                log.info("============> Invalid Token");
            }
        } catch (Exception e) {
            e.printStackTrace();
            SecurityContextHolder.clearContext();
        }

        */

    filterChain.doFilter(request, response);
  }

  public AppUser validateCamdigikeyToken(String token)  {
/* 
        if (true) {
          
            Map<String, Object> responseData = (Map<String, Object>) response.get("payload");

            String domain = null;
            if (responseData != null && !responseData.isEmpty() && responseData.containsKey("domain")) {
                domain = (String) responseData.get("domain");
            }

            CamDigiKeyUserInfoResponse camDigiKeyUserInfoResponse = CamDigiKeyUserInfoResponse.builder()
                    .personalCode((String) responseData.get("personal_code"))
                    .camdigikeyId((String) responseData.get("camdigikey_id"))
                    .lastNameKh((String) responseData.get("last_name_kh"))
                    .firstNameKh((String) responseData.get("first_name_kh"))
                    .lastNameEn((String) responseData.get("last_name_en"))
                    .firstNameEn((String) responseData.get("first_name_en"))
                    .email((String) responseData.get("email"))
                    .mobilePhone((String) responseData.get("mobile_phone"))
                    .nationality((String) responseData.get("nationality"))
                    .dob((String) responseData.get("dob"))
                    .gender((String) responseData.get("gender"))
                    .build();

            if (!ObjectUtils.isEmpty(camDigiKeyUserInfoResponse)) {
                String username = String.format("%s-%s", camDigiKeyUserInfoResponse.getFirstNameEn().toUpperCase(), camDigiKeyUserInfoResponse.getCamdigikeyId());
                if (ObjectUtils.isEmpty(camDigiKeyUserInfoResponse.getPersonalCode())) {
                    throw new UnAuthorizationException("Personal code is null! Please contact Camdigikey Technical Support!");
                }

                AppUser appUserOptional = appUserRepository.findAppUserByPersonalCode(camDigiKeyUserInfoResponse.getPersonalCode()).orElse(null);
                if (appUserOptional==null) {
                    AppUser appUser = AppUser.builder()
                            .username(username)
                            .camdigikeyId(camDigiKeyUserInfoResponse.getCamdigikeyId())
                            .personalCode(camDigiKeyUserInfoResponse.getPersonalCode())
                            .identityNumber(camDigiKeyUserInfoResponse.getPersonalCode())
                            .dateOfBirth(camDigiKeyUserInfoResponse.getDob())
                            .lastnameKh(camDigiKeyUserInfoResponse.getLastNameKh())
                            .firstnameKh(camDigiKeyUserInfoResponse.getFirstNameKh())
                            .lastnameEn(camDigiKeyUserInfoResponse.getLastNameEn())
                            .firstnameEn(camDigiKeyUserInfoResponse.getFirstNameEn())
                            .gender(EnumGender.valueOf(camDigiKeyUserInfoResponse.getGender()))
                            .email(camDigiKeyUserInfoResponse.getEmail())
                            .mobilePhone(camDigiKeyUserInfoResponse.getMobilePhone())
                            .firstTimeLoginRemaining(false)
                            .lastTimePasswordUpdated(new Date())
                            .password(passwordEncoder.encode(""))
                            .enabled(true)
                            .firstSignInDomain(domain)
                            .build();

                    AppRole appRole = appRoleRepository.findRoleByCode(EnumAppRole.CAMDIGIKEY_PUBLIC_USER.name())
                            .orElseThrow(() -> new InvalidDataException("500", "Role not found!", HttpStatus.NOT_FOUND));

                    appUser.setRoles(Set.of(appRole));
                    appUser.setAppUserStatus(AppUserStatus.ASSIGNED);
                    log.info("Added New user");
                    return appUserRepository.save(appUser);
                }else {
                    if(!appUserOptional.isEnabled()){
                        throw new UnAuthorizationException("Account has been disabled!");
                    }
                    appUserOptional.setCamdigikeyId(camDigiKeyUserInfoResponse.getCamdigikeyId());
                    appUserOptional.setPersonalCode(camDigiKeyUserInfoResponse.getPersonalCode());
                    appUserOptional.setDateOfBirth(camDigiKeyUserInfoResponse.getDob());
                    appUserOptional.setIdentityNumber(camDigiKeyUserInfoResponse.getPersonalCode());
                    appUserOptional.setLastnameKh(camDigiKeyUserInfoResponse.getLastNameKh());
                    appUserOptional.setFirstnameKh(camDigiKeyUserInfoResponse.getFirstNameKh());
                    appUserOptional.setLastnameEn(camDigiKeyUserInfoResponse.getLastNameEn());
                    appUserOptional.setFirstnameEn(camDigiKeyUserInfoResponse.getFirstNameEn());
                    appUserOptional.setGender(EnumGender.valueOf(camDigiKeyUserInfoResponse.getGender()));
                    appUserOptional.setEmail(camDigiKeyUserInfoResponse.getEmail());
                    appUserOptional.setMobilePhone(camDigiKeyUserInfoResponse.getMobilePhone());
                    log.info("==============> Updated user");
                    return appUserRepository.save(appUserOptional);
                }
            }
            
            return new AppUser();
        }else {
            log.info("Invalid Token");
        } */
        return new AppUser();
       // throw new UnableToSendNotificationException("Extract CamDigiKey User Info Failed");
    }
}
