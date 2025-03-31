
import "./globals.css";

export const metadata = {
  title: "MurallaGraph",
  description: "GIS",
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        {children}
      </body>
    </html>
  );
}
