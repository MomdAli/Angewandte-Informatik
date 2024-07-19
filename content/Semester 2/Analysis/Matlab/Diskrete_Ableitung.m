clc, clearvars, close allglobal alpha beta gamma

%% Datensatz - gegebene Punkte eintragen! 
% Eingabe der Punkte im Stil P = [x1 y1]; [x2 y2]; [...]; [xn yn]];
P = [[0 0.25]; [0.2 -0.25]; [0.4 -0.63]; [0.6 -0.63]; [0.8 -0.25]; [1 0.25]; [1.2 0.71]];

%% Bestimmen von Schrittweite h und Anzahl der Punkte
h = mean(diff(P(:, 1)));
anzahl = length(P);
fprintf('Wir betrachten %d Datenpunkte mit einer Schrittweite von  %.2e\n', anzahl, h)

%% Definition der diskreten Ableitungen (gemittelt)
% erste Diskrete Ableitung:
D1 = (diag(ones(anzahl-1, 1), 1) - diag(ones(anzahl-1, 1), -1))/2;
D1 = D1/h;

% zweite diskrete Ableitung:
D2 = -2*diag(ones(anzahl,1))+diag(ones(anzahl-1,1),1)+diag(ones(anzahl-1,1),-1);
D2 = D2/h/h;

%% Bestimmen der Ableitungen für unsere Daten:
% erste Ableitung:
d1P = D1 * P(:, 2)
% zweite Ableitung:
d2P = D2 * P(:, 2)

%%  Bestimmen der Nullstellen
% Nullstellen von Datensatz P - Nullstellen der Fkt
IndNS = find(P(1 : end-1, 2) .*P(2 : end, 2) < 0);
xNS = (P(IndNS, 1) + P(IndNS+1, 1)) / 2;
fprintf('Die Funktion hat eine Nullstelle bei etwa (%.2e | 0) \n', xNS)


% Nullstellen von 1. Ableitung - Extrema der Fkt
IndKrit = find(d1P(1:end-1).*d1P(2:end)<0);
fprintf('Die Funktion hat %d kritische Punkte: \n', length(IndKrit))
% erster Punkt:
xEP = (P(IndKrit, 1) + P(IndKrit+1, 1)) /2;
yEP = (P(IndKrit, 2) + P(IndKrit+1, 2)) /2;
fprintf('- P1(%.2e | %.2e) \n', xEP, yEP)

% Nullstellen von 2. Ableitung - Wendepunkte der Fkt
IndWen=find(d2P(1:end-1).*d2P(2:end)<0);
fprintf('Es gibt ggfs. %d Wendepunkte: \n', length(IndWen))
xWP = (P(IndWen, 1) +P(IndWen+1, 1))/2;
yWP = (P(IndWen, 2) + P(IndWen+1, 2)) /2;
fprintf('-P(%.2e | %.2e)\n', xWP, yWP)


%% Plotten des Datensatzes
 % Plotten des Datensatzes
plot(P(:, 1), P(:, 2), 'ro-')
grid on
hold on
% Plotten der ersten Ableitung
plot(P(:, 1), d1P, 'bo-')
% Plotten der zweiten Ableitung
plot(P(:, 1), d2P, 'mo-')
% Plotten der Extrem- und Wendepunkte:
plot(xEP, yEP, 'r*')
plot(xWP, yWP, 'r*')

% Plotten der Ansatzfunktion - einkommentieren wenn fertig
%xx = min(P(:, 1)):0.1:max(P(:,1));
%plot(xx, Ansatzfunction(xx), 'k-')

legend('Datensatz', '1. Ableitung', '2. Ableitung')


%% Bestimmte Parameter eintragen
beta = 1;
alpha = 2;
gamma = 3;

%% Ansatzfunktion 

function y = Ansatzfunction(x)
global alpha beta gamma
    y = 2; % Ansatzfunktion eintragen
end