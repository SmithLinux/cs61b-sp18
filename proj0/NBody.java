public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int firstItem = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        double xxPos;
        double yyPos;
        double xxVel;
        double yyVel;
        double mass;
        int count = 0;
        String imgFileName;
        In in = new In(fileName);
        int numberOfPlanet = in.readInt();
        Planet[] planets = new Planet[numberOfPlanet];
        double radius = in.readDouble();
        while (count < numberOfPlanet) {
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            imgFileName = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planets[count] = p;
            count++;
        }
        return planets;
    }

    public static void main(String[] args) {
        double timeStart = 0;

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];

        while (timeStart < T) {
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            int index = 0;


            for (Planet p: planets) {
                xForces[index] = p.calcNetForceExertedByX(planets);
                yForces[index] = p.calcNetForceExertedByY(planets);
                index++;
            }

            for (Planet p: planets) {
                p.draw();
            }
            index = 0;



            StdDraw.show();
            StdDraw.pause(10);
            timeStart += dt;

            for (Planet p: planets) {
                p.update(dt, xForces[index], yForces[index]);
                index++;
            }
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
