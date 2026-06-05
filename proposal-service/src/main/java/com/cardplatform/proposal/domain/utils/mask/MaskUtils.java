package com.cardplatform.proposal.domain.utils.mask;

public final class MaskUtils {

  public static String maskCpf(String cpf) {
    if (cpf == null || cpf.length() < 4) {
      return "***";
    }

    return "***.***.***-" + cpf.substring(cpf.length() - 2);
  }
}
