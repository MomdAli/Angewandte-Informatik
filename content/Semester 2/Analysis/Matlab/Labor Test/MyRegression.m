clear all
close all

% Aufgabe: Finden Sie optimale Parameter fuer den Datenfit von GlockeR.dat
% (1) passenden Datensatz einlesen
% (2) Ansatzfunktion anpassen

global P 

P=load("Glocke.dat");
%P=load("influenza_england_1978_school.dat");
%P=load("Covid-DE-I.dat");

c0 = [0 0 0]; 
[c res] = lsqnonlin(@residuum,c0);


fprintf('error = %.2e\n',sqrt(res));
fprintf(['optimal parameter: c = [%.2e,%.2e,%.2e]\n'],c);

%%
h = figure(1);
xx = linspace(min(P(:,1))-0.2,max(P(:,1))+0.2,100);
yy = ansatzfunction(xx,c);
plot(P(:,1),P(:,2),'ro');
grid on
hold on
plot(xx,yy,'b-');
ylim([min(P(:,2)),max(P(:,2))])
set(gca,'XAxisLocation', 'origin', 'YAxisLocation', 'origin')
legend('Daten','Datenfit')

%%
function r = residuum(c)
global P

r = P(:,2)-ansatzfunction(P(:,1),c);

end

function y=ansatzfunction(x,c)

y = c(1)+c(2)*x+c(3)*x.^2; 

end