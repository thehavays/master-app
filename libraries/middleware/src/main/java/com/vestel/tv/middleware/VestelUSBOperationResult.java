package com.vestel.tv.middleware;

/**
 * This enumeration defines possible return codes for USB operations
 */
public enum VestelUSBOperationResult
{
    ci_plus_update_with_path_fail,
    ci_plus_key_copy_fail,
    ci_plus_ecp_update_with_path_fail,
    ci_plus_ecp_key_copy_fail,

    hash_key_copy_success,
    hash_key_copy_fail,

    hdcp_14_key_copy_success,
    hdcp_14_key_copy_fail,
    hdcp_20_key_copy_success,
    hdcp_20_key_copy_fail,
    hdcp_20_key_encrypt_fail,

    playready_25_key_priv_copy_fail,
    playready_25_key_cert_copy_fail,
    playready_25_key_copy_success,
    playready_25_key_encrypt_fail,
    playready_30_key_priv_copy_fail,
    playready_30_key_cert_copy_fail,
    playready_30_key_copy_success,
    playready_30_key_encrypt_fail,
    playready_40_key_priv_copy_fail,
    playready_40_key_cert_copy_fail,
    playready_40_key_copy_success,
    playready_40_key_encrypt_fail,

    widevine_key_copy_fail,
    widevine_key_copy_success,
    widevine_key_encrypt_fail,
    widevine_v9_key_copy_fail,
    widevine_v9_key_copy_success,
    widevine_v9_key_encrypt_fail,

    nagra_key_copy_fail,
    nagra_key_copy_success,
    nagra_key_encrypt_fail,

    attestation_key_copy_fail,
    attestation_key_copy_success,
    attestation_key_encrypt_fail,

    freeviewplay_key_copy_fail,

    irconfig_file_copy_fail,
    irconfig_file_name_update_fail,
    irconfig_file_mboot_env_db_update_fail,
    irconfig_file_update_success,

    customization_file_copy_fail,
    customization_file_mboot_env_db_update_fail,

    tv_wizard_country_file_copy_fail,

    netflix_esn_key_copy_fail,
    netflix_kpekph_key_copy_fail,
    netflix_kpekph_key_encrypt_fail,

    boot_logo_too_large,
    boot_logo_copy_fail,
    boot_logo_name_update_fail,
    boot_logo_mboot_env_db_update_fail,
    boot_logo_update_success,

    boot_music_too_large,
    boot_music_copy_fail,
    boot_music_name_update_fail,
    boot_music_mboot_env_db_update_fail,
    boot_music_update_success,

    hdmi_14_edid_copy_success,
    hdmi_14_edid_copy_fail,
    hdmi_20_edid_copy_success,
    hdmi_20_edid_copy_fail,
    hdmi_21_edid_copy_success,
    hdmi_21_edid_copy_fail,
    vga_edid_copy_success,
    vga_edid_copy_fail,

    board_file_copy_fail,
    board_file_mboot_env_db_update_fail,
    board_file_name_update_fail,
    board_file_update_success,

    panel_file_copy_fail,
    panel_file_name_update_fail,
    panel_file_update_success,

    config_default_value_file_copy_fail,

    pq_file_copy_fail,
    pq_general_file_copy_fail,
    pq_hdr_bin_file_copy_fail,
    pq_hdr_1611_bin_file_copy_fail,
    pq_gamma_file_copy_fail,
    pq_osd_mapping_file_copy_fail,
    pq_color_file_copy_fail,

    aq_file_copy_fail,
    aq_amp_file_copy_fail,
    aq_input_source_file_copy_fail,
    aq_sound_style_file_copy_fail,
    aq_dap_file_copy_fail,
    aq_dts_vx_file_copy_fail,

    tcon_binary_file_copy_fail,
    tcon_gamma_file_copy_fail,
    tcon_ini_file_copy_fail,
    tcon_pmic_file_copy_fail,
    tcon_level_shift_file_copy_fail,
    tcon_mboot_env_db_update_fail,

    mfc_firmware_update_fail,
    mfc_firmware_same_version,

    ursa_orbit_config_file_copy_fail,

    thermal_mapping_file_update_fail,

    ldm_file_copy_fail,
    ldm_file_update_success,
    ldm_file_update_skipped
}