% Aufgabe 3.m
% 28.06.2024
clc, clearvars, close all

influenza_data = load("Aufgabe3\influenza_england_1978_school.dat");
covid_data = load("Aufgabe3\Covid-DE-I.dat");

x_influenza = influenza_data(:, 1);
y_influenza = influenza_data(:, 2);

x_covid = covid_data(:, 1);
y_covid = covid_data(:, 2);

%% Plotten der Funktion
figure 
subplot(1, 2, 1);
plot(x_influenza, y_influenza, 'r', LineWidth=1.25);
hold on
plot(x_influenza, smoothdata(y_influenza), 'm', LineWidth=1.25);
legend('Influenza', 'Influenza smoothed');
xlabel('Zeit in Tagen');
ylabel('Neu infizierten Personen');
title('Einer Epidemie einer englischen Schule von 1978 ');
grid on

subplot(1, 2, 2);
plot(x_covid, y_covid, LineWidth=1.25);
hold on
plot(x_covid, smoothdata(y_covid), 'c', LineWidth=1.25);
legend('Covid', 'Covid smoothed');
xlabel('Zeit in Tagen');
ylabel('Neu infizierten Personen');
title('Covid-Epidemie in Deutschland');
grid on

%% Charakteristischen Größen
x = influenza_data(:, 1); f = influenza_data(:, 2);
