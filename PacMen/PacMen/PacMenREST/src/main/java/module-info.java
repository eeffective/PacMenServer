module PacMenREST {
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.web;
    requires spring.context;
    requires spring.core;
    requires spring.beans;

    opens com.fhict.pacmenrest to spring.core;

    exports com.fhict.pacmenrest;
    exports com.fhict.pacmenrest.controllers;
    exports com.fhict.pacmenrest.models;
    exports com.fhict.pacmenrest.services;
}
