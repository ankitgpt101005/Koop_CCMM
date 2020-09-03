--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-08 21:58:43

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 17112)
-- Name: aacc; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA aacc;


ALTER SCHEMA aacc OWNER TO postgres;

SET default_tablespace = '';



--
-- TOC entry 203 (class 1259 OID 17113)
-- Name: agent_queued; Type: TABLE; Schema: aacc; Owner: postgres
--

CREATE TABLE aacc.agent_queued (
    context character varying NOT NULL,
    queued integer,
    skill_set_id bigint
);


ALTER TABLE aacc.agent_queued OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17119)
-- Name: ccmm_meta; Type: TABLE; Schema: aacc; Owner: postgres
--

CREATE TABLE aacc.ccmm_meta (
    context character varying NOT NULL,
    session_key character varying NOT NULL,
    contact_id character varying NOT NULL,
    cust_id character varying,
    chat_id character varying,
    agent_id character varying
);


ALTER TABLE aacc.ccmm_meta OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 17125)
-- Name: conversation_timestamp; Type: TABLE; Schema: aacc; Owner: postgres
--

CREATE TABLE aacc.conversation_timestamp (
    context character varying NOT NULL,
    "timestamp" bigint
);


ALTER TABLE aacc.conversation_timestamp OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 17131)
-- Name: customer; Type: TABLE; Schema: aacc; Owner: postgres
--

CREATE TABLE aacc.customer (
    context character varying NOT NULL,
    name character varying,
    email character varying,
    phone character varying
);


ALTER TABLE aacc.customer OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 17769)
-- Name: ewc_meta; Type: TABLE; Schema: aacc; Owner: postgres
--

CREATE TABLE aacc.ewc_meta (
    context character varying NOT NULL,
    auth_key character varying NOT NULL,
    guid bigint,
    agent_id character varying,
    chat_id character varying
);


ALTER TABLE aacc.ewc_meta OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 17143)
-- Name: koopid_meta; Type: TABLE; Schema: aacc; Owner: postgres
--

CREATE TABLE aacc.koopid_meta (
    context character varying NOT NULL,
    sender_name character varying NOT NULL,
    path character varying,
    sender character varying,
    tz character varying,
    ack boolean,
    text character varying,
    id character varying,
    type character varying,
    mediatype character varying,
    ts bigint
);


ALTER TABLE aacc.koopid_meta OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 17149)
-- Name: koopid_provider; Type: TABLE; Schema: aacc; Owner: postgres
--

CREATE TABLE aacc.koopid_provider (
    partner_id character varying NOT NULL,
    provider_id character varying NOT NULL,
    prefix character varying,
    api_key character varying,
    domain character varying,
    webchat_type character varying,
    config_objects jsonb,
    auth_key character varying
);


ALTER TABLE aacc.koopid_provider OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 17760)
-- Name: route; Type: TABLE; Schema: aacc; Owner: postgres
--

CREATE TABLE aacc.route (
    context character varying NOT NULL,
    provider_id character varying,
    is_active boolean NOT NULL,
    is_polling_started boolean DEFAULT false NOT NULL,
    partner_id character varying,
    skill character varying,
    type character varying
);


ALTER TABLE aacc.route OWNER TO postgres;

--
-- TOC entry 2724 (class 2606 OID 17163)
-- Name: agent_queued agent_queued_pkey; Type: CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.agent_queued
    ADD CONSTRAINT agent_queued_pkey PRIMARY KEY (context);


--
-- TOC entry 2726 (class 2606 OID 17165)
-- Name: ccmm_meta ccmm_meta_pkey; Type: CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.ccmm_meta
    ADD CONSTRAINT ccmm_meta_pkey PRIMARY KEY (context);


--
-- TOC entry 2728 (class 2606 OID 17167)
-- Name: conversation_timestamp conversation_timestamp_pkey; Type: CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.conversation_timestamp
    ADD CONSTRAINT conversation_timestamp_pkey PRIMARY KEY (context);


--
-- TOC entry 2730 (class 2606 OID 17169)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (context);


--
-- TOC entry 2738 (class 2606 OID 17776)
-- Name: ewc_meta ewc_meta_pkey; Type: CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.ewc_meta
    ADD CONSTRAINT ewc_meta_pkey PRIMARY KEY (context);


--
-- TOC entry 2732 (class 2606 OID 17171)
-- Name: koopid_meta koopid_meta_pkey; Type: CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.koopid_meta
    ADD CONSTRAINT koopid_meta_pkey PRIMARY KEY (context);


--
-- TOC entry 2734 (class 2606 OID 17173)
-- Name: koopid_provider koopid_provider_pkey; Type: CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.koopid_provider
    ADD CONSTRAINT koopid_provider_pkey PRIMARY KEY (partner_id, provider_id);


--
-- TOC entry 2736 (class 2606 OID 17768)
-- Name: route route_pkey; Type: CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.route
    ADD CONSTRAINT route_pkey PRIMARY KEY (context);


--
-- TOC entry 2739 (class 2606 OID 26028)
-- Name: agent_queued agent_queued_fkey; Type: FK CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.agent_queued
    ADD CONSTRAINT agent_queued_fkey FOREIGN KEY (context) REFERENCES aacc.route(context) NOT VALID;


--
-- TOC entry 2740 (class 2606 OID 26209)
-- Name: ccmm_meta ccmm_meta_fkey; Type: FK CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.ccmm_meta
    ADD CONSTRAINT ccmm_meta_fkey FOREIGN KEY (context) REFERENCES aacc.route(context) NOT VALID;


--
-- TOC entry 2741 (class 2606 OID 26242)
-- Name: conversation_timestamp conversation_timestamp_fkey; Type: FK CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.conversation_timestamp
    ADD CONSTRAINT conversation_timestamp_fkey FOREIGN KEY (context) REFERENCES aacc.route(context) NOT VALID;


--
-- TOC entry 2742 (class 2606 OID 26247)
-- Name: customer customer_fkey; Type: FK CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.customer
    ADD CONSTRAINT customer_fkey FOREIGN KEY (context) REFERENCES aacc.route(context) NOT VALID;


--
-- TOC entry 2744 (class 2606 OID 26252)
-- Name: ewc_meta ewc_meta_fkey; Type: FK CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.ewc_meta
    ADD CONSTRAINT ewc_meta_fkey FOREIGN KEY (context) REFERENCES aacc.route(context) NOT VALID;


--
-- TOC entry 2743 (class 2606 OID 26257)
-- Name: koopid_meta koopid_meta_fkey; Type: FK CONSTRAINT; Schema: aacc; Owner: postgres
--

ALTER TABLE ONLY aacc.koopid_meta
    ADD CONSTRAINT koopid_meta_fkey FOREIGN KEY (context) REFERENCES aacc.route(context) NOT VALID;


-- Completed on 2020-04-08 21:58:44

--
-- PostgreSQL database dump complete
--

