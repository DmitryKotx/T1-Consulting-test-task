package ru.kotov.T1ConsultingTestTask.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CharacterFrequencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCharacterFrequencies() throws Exception {
        mockMvc.perform(get("/api/character-frequencies")
                        .param("inputString", "Aabaabab"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"character\":\"a\",\"frequency\":4},{\"character\":\"b\",\"frequency\":3}, {\"character\":\"A\",\"frequency\":1}]"));

        mockMvc.perform(get("/api/character-frequencies")
                        .param("inputString", "Aabaabab")
                        .param("ignoreCase", "false"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"character\":\"a\",\"frequency\":4},{\"character\":\"b\",\"frequency\":3}, {\"character\":\"A\",\"frequency\":1}]"));

        mockMvc.perform(get("/api/character-frequencies")
                        .param("inputString", "Aabaabab")
                        .param("ignoreCase", "true"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"character\":\"a\",\"frequency\":5},{\"character\":\"b\",\"frequency\":3}]"));
    }
    @Test
    public void testInvalidParameterFormat() throws Exception {
        mockMvc.perform(get("/api/character-frequencies")
                        .param("inputString", "Aabaabab")
                        .param("ignoreCase", "invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The ignoreCase parameter must be true or false."));
    }

    @Test
    public void testInvalidInputFormat() throws Exception {
        mockMvc.perform(get("/api/character-frequencies")
                        .param("inputString", ""))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The input string must not be empty."));

        mockMvc.perform(get("/api/character-frequencies")
                        .param("inputString", """
                                XXRvgHgSbvzGNDtzEDaJyJmtNjlwSUNGfNboaHTEUeQkoIiJXLrLvPNFGeyXYxJtejnoGReltMqczuIPNNSZhmPhrfpzIPNkZGUpwwOApSeBmLIndirvJBxMygGqrUSIMpujkRzKEUwzIbXvFKwsnZNplWSbZJTaumZmnmMaPKeaDnATvIiFIUntrhewsfpZARIPPUaVkNhyLbYXgjUdbOLpVfpMlNRxHszERfIjLvVJRUtjbWaVYwaAIwwhEcOXgooNdASXISCRopHdPrLxxYRMCAsVrHzZSzmOsIXInzCtMvCSJUiwTKgVjIWiTLxBOEVXLRgCRjHeevAospcjCMmNKNJuxpEOeMJubxtnKBIOkvzfDFMIdsYXvoTcpCvSaqxjSuFPFINHyIDLHmslQQwQucZMVAPGkdeKYpjXWzKitgoQAZDpMzAuxpUGBNxaOAYfPVfYmncNuGXuSsyiEgzVIgJQXeMjeLmyynbHzvQeXmRyJnlQggwxZMNBftKJThBIaHtuibzbpENxbhAIiBJMWjNJmKhfEekgoHXoWRaliGidDOkxmqKPqMkRyreULMfXghHUSKWknKuyWtbUpqjqOjMlQiGzRaoZVDyPNOYYjgvBWvJNCisFLVzwnmcWUBKvqLAoWHsPvJhiRGBnSAPRUmtbQZVJNDGipbCObQrNPrTOgerzlbqBfeHKjSYaHbLjwfhhvniBIMDnEiSTCuXUBgMBabCxyaiuhTvuWzupYUAkZQxxqBPwOfDLffXkQbzVIRWFTGPHkoKHOBJvYSVQwZohqJPoKtTcldSXRhmgoKysFahwicayepdVLPlluLHgJsmMQnIbEgaiFCzuLXKiBclpcNCSPLXtPSAZoQQMHrTiMmlstOAoTvDhWZVMzAjSRvjxYwaaJxVzWZZrdWTZFVPbZzaPvNuTtgHiWektJRuXZbilmdSTAgRrzMpuZDKfnctymjUCSYoUiwsVZisWnMaUkoTjBJUATAF
                                hNBXqCXMWOLdadEAxQcRJaIlOvInJEOOgOAiuYOkOIazwswIEOPFxKKajJiVRaXBmYGNRkQJarfIEPYabhIAdRBkBjXEIGbCAbArlXJdsDRiZpwWrDqyUMDrohBBghBNpSOacMLcmkHbwnKwcuDtFamcTChRSjIxclLqZaKmavVwoPBiPlPankfVyZShnsriwdepmvDYJPGWLjMilLLOsRZTwLppvSvVteVVwOobETaaRPhTXlzYNcDguqMKRrJkLfMHxWbDdTGGxCpFzlMgdBCjJpSZtiofTklhsxDHMrWuAOXdoSWYxyOwnsikvoLyJwSeODNRdNLqzSqIZgBpLyAtOidGzJyOhbVcmVDHKYiCPcicDImPHAMbQDhJeJMkkWFIRzlSgCBMcyWeTaKnjDrjuWSZaOWufDFululKTchgZjtIOMnIuCTnQEVjfgBGoffPfWtueQDyDseaoRyVkBBnrhAXCqKTjZTZidfRqCilSvEdhhPekoOCmJWVfVxPBBiutrlAXQFdxwjcimbGxfrIMSOoagEhUrQyLtLYfkTyCqcCGxKPwUoKKADlPNTiqscTUglprSoOeHYVzHlxfcqDHQShVLnSsdaCUhNdEGdRKxjfDIcchATpQxlDSSHXWadCkgbyGuVuQsyCEoWjRpoLGIrQFqIhzbcCQaKoVOOldQWkyTLlKtOnYyJZgMBElyHKXZGuaWybXItjLRChPIpIFyvnFKVyoTlRlFspEnWjzdeKcYevYlQXXvGxAGDIVKCfaIdFgetxzQSiqXKJvzfEFGtrwGqQUOzBaQKeYTYPNjsWyjnHvjkjcrORqCqkNnLiWFocZjLoxroAJigcNVZbJzmuNIdvMKshZlAFdpSWzoayoFerCgsQJSyFvNPBbqcCXPKLjHvAiPLmdLQJEfTKXuetallOorOcWQQXDGhyBtgTNGKhuYataJWswhFiLRHCKYZVzSzECerOOuQDRXBCUOytqwRiVKMdvIi
                                FwjDCwVlRCrYjPSPLUzFTVMiVKaqpyYOatnmNqqnQZBrRaKhxOlFSSyKIHmxaeUCsazjuxMdPuUrUHyFrnTNUQzMmTaALVLxXBfVWsWnhRAANdpiwwPdGXOVZUdxCwkLzGcEbTIxIshBVGcAcGlceUdpcDsWVvtcfxkxZTwRilqjLmeBXIfSyMPRQdnxqpPyrFcJZermDxVtvAebicwHlnHbsBxmaXDVvhchohKWoPtdafpRaNEhJCluByfKqqxLsjOjVZhDPuiJlzMMBNsYxmwbwDbTdlHtwlPFecheLlfaDzeqDPdZdalZYdZoHQYgHnUWSzuUyGdKHJqBGwjhvfTleqqzxzKSiOzxiCVqGRcvIVlRqLhyVbiYsFfSvZZxkLfoMVwgdgKbBQqFZndSAQqENzKqOmhNOfrWuNoJNvdhxlmoDKzrriMlOVNYzKFLWQKxZgtYDqldpNeUbRWPnlbEBwIzqNOdkShOOJaHUuIXMdVHiUlDlTGAGIFZoTJGNgoRrVQQbSmFeOUaixtAFUmRrskCzmceilbEhBlwXVtxcEFfssasHGMGflowQKjVPnqsFrLJIhNReTjCMcYnSQhCGZVjdcBWixqSrJeaBCLyfFSWZJiwTmBSCbmNDRkusgBUdadAioQdpdYvnnlPycWTXUGbIsAdijGLTcFtzkMnqLTjqeHysNkQckpLcqOJpPLjmzRbtgQKrGnMgJqMIBojerIKHELqdQRjjFeJeExtWzsUMpfOcSRLbfZalmsDlEDUkkftILiKzXwVdZLsJIBRXeQvmLNVbLsnzpSuRwlcOjJfGQPcWryUBuTAbDsBkKtMYTOPglRaIKNDMNFahcfMXCffnMLiZWehPJPhzcPywZiJpkvumYwFBxstPCiTDmGKItkITMzOAUSjqppkDJqifTbTvnUkWyAUmVyWrGDEgWkrTKEsahRvefORNyHjAtkEJvsclTRobOEXlsBXcPOefGSykXanIqPDtzv
                                qyVGrGEDqIWOZQWoogdUeuvWUTXmLrjXmKcEPsjbsAWVITBIodRzBViNzewDJtghDfADePCQuesacGLWOVLXtnviVTGpazUBexJfZPNfSLeHFOCXVnltaThsIkRTTDTPixAQUhAAJYHBKnTrhmWDiVzZbeAUWgSbLHTRXBwwjdjtcPaSppcrZyyXiMROpZsewKXfBzUAPSVEIfhjZvXyWHkdfdgmJdJkjrHymbDwFnxLXnJsPENzoWBZGaBUTyvjwtRnsimszYdBhjGpRKXBLeHFqbmVEcwNWaQXvkzmFwsDqJkJecbwUhkyJhaBnsvnzPpMoehyoxhxUXKUAqWgizKYuXbSzDTJixvpBIPNMYcVdydqrVOItQynaEoYjRdDYNaSTzOHDGHnYJFCkPFucpVdmpmDIAmpGSPhtSGqjGxCtDnqsCAYIKPMpBcXincxCIzpJiNPguoqDbJTIdmsgHnZuJCGQtegZbXbChwUXtejYULNpOViBQHchJbbYaSRTEeFRNLgbmuIlDmDRYNHBcYqhwahSbbVUlNcMllfEerLWlKBZOdXghYVLguqwpKhYmybBUCgUsvUhWZngIjvVzHFEIKFYEjyUUesrMHSkoCeEOlcsANeBiStgVHkhSuqFbWoZNHVGYtcfGaQieeOTYsjWGXHGsHoSaXFPveRvVzveMUiLGdjQUQsYyiaiNdjHWyLkjgrIMyroXOPQAcGJRiwuXEJVKJwKBqrssVMMxUHNhdRjREEurdGjVmImwxaMGAWPcIyOeeixhQMVdUdjyMXquseEUaJgEJeZIzThIvCIUIGUpySufQqjpOpWBpQYnBFzdMJjRVZigrEjMeJtPxUcLtjAFPXcRUUJXlQPWGdiEMGVAUTBZRefAyddOzricKYNpmgEiQlWdqwDtvNJYneIGTNnRDyrElqtdUgpdkuDFzxjfWqvMyARnfKkEMFiXeRxMzlsEioLpmFUFBDdHZoCoxjKJKgbWKZEXq
                                pEAzkGjiJwqdVqoGTAbhNWGdstGVsCzVyXyhiuxWNiSERpmwIZHtFclmCzACHIPhvLnFzvzkyoJxcegJkUOJQvawVxhtLVUlURsCxOtHgnbrJyLYcqDLKHCAoSGOkrNYUapBRFtbxZdTJUyLFOLujkcFeTKjTvrANQEJbOoPjMhUwtFqmPxluswREogZsArqNHyojgQyosDiXvgBbsdNbnqiTZhWdxabmgLmLZnXEVcGPFDnwyiRDfHkHgHsnkwGMdykAGSoOXpdCmaEGbQzqIGIzVvXawpYLVsdveQAKnBHvbWRyaakZUiqwdlHQasbfUcXLutPmqNmOjBbGHTCMWwTkYjlrGiTJjLhFOkXWyxslgrplHNCEYgEatNdKeLpkjBNprjlJfUxwVpQPYgwGppEcBHimGltcBXvkmOBXRFLuFcLGllKNzynBFtRigthsVHNaXXwEXVPrReZQTcMtBFSMmWSopgpMjeZeHNqIDXmZLAVmnSLxBDhtJvabsjPkjnARxUHtMgcyWiVeOqyaFXRNZfeEzQtuOkIpeHbLFieYEhLdvvIKDRMbvxlESumdEuAkmzeOXiGtWHnCSVyXjOLGnHvWNLMckZdoxMcGTEnzsushDxZciMXYJauQwMzzNDGSBvyzCrBylVlhvNfmEkokZSkvECpvlkOAlJyhHfvsMmDzVpyHjeVCOGybWPtLgQBCPJqxPtaqeRUeCoSODaXgwubzUTBzWyTZhlzdPmnTZTRuBPcwPkWTpOuqGkfYVuFmppvRYToHLfLXXemBAYXNfZohcdAxdVuwOoMzOxSxifIUBpOtTpRFMfrXirdpTRihZXORzkSKFXyMgUgxlNvGPrVQmzcsEGxcLtYYQiufZAAyIgthczqecpjEtgjSRocpfyCTqOuPcATpwWVPxPevenqNhsLqHNsqntDgJWaNfnNTitksORIPMlXmOfaxpGdmXhqVePKilIGWXujYtKXJbuObKMYXIglzQV
                                kMKWazaiEiYoLIchJajwwqKKBDvhosbtJklpsMJSaAVsvSjzqBroygfZxNDCCFsICXiuUDvZMHlufBCiKcHdiIaamJahVXbLYdTIKYoNrwvTdtGEDqlXEJXuNskKeNKyNXghKHQlzDncvVYIrsHJarxmGVZDBIkSusjkZxUBqEPspsTpmoCHaEzqvmcsudpnWwYBmwVOLHAndtYxMWbMxzIfEhCQHlxxWExbobyXUfOMswwmdfEUUUyaleiJstUKgkBSeAkiDWlnUNOZswiAysIoZXaKoFisdKhtwCawFIaZSmYKoQTePTqmUjIrKfJgdwrEZIJeDZXqgbBxrKnbnaihNjFdnhztjPUsnOtebhraASmLgeQYmAoStFjQtEXyyLFPMddKZUQFoOsTVnGkLAJbXlmUizOTJfsZpIpTBIqYVrJPujZovQCIbsanzuhEKNnEckqyzddStTIFcebcyaVGWjAbaCaRWEJuNsfaggcLwMvEfLHDexftTbaxsHwtzTVFWzRpVdJyQORQpMJzslxFapYYBGsiepvsJUtSSw"""))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The length of the input string must be" +
                        " less than 5000 characters."));
    }
}