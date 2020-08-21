package com.koopid.ccmm.entity;

public class ConfigObjects {

	private AgentConfig agentConfig;

	private PartnerConfig partnerConfig;

	private WebchatConfig webchatConfig;

	private ProviderConfig providerConfig;

	private MessageWhitelist messageWhitelist;

	public MessageWhitelist getMessageWhitelist() {
		return messageWhitelist;
	}

	public void setMessageWhitelist(MessageWhitelist messageWhitelist) {
		this.messageWhitelist = messageWhitelist;
	}

	public PartnerConfig getPartnerConfig() {
		return partnerConfig;
	}

	public void setPartnerConfig(PartnerConfig partnerConfig) {
		this.partnerConfig = partnerConfig;
	}

	public ProviderConfig getProviderConfig() {
		return providerConfig;
	}

	public void setProviderConfig(ProviderConfig providerConfig) {
		this.providerConfig = providerConfig;
	}

	public AgentConfig getAgentConfig() {
		return agentConfig;
	}

	public void setAgentConfig(AgentConfig agentConfig) {
		this.agentConfig = agentConfig;
	}

	public WebchatConfig getWebchatConfig() {
		return webchatConfig;
	}

	public void setWebchatConfig(WebchatConfig webchatConfig) {
		this.webchatConfig = webchatConfig;
	}

	@Override
	public String toString() {
		return "ConfigObjects [agentConfig=" + agentConfig + ", partnerConfig=" + partnerConfig + ", webchatConfig="
				+ webchatConfig + ", providerConfig=" + providerConfig + ", messageWhitelist=" + messageWhitelist + "]";
	}

}
