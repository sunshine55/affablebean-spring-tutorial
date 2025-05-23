export default function Footer() {
  const currentYear = new Date().getFullYear()

  return (
    <footer className="border-t bg-background py-4 text-center text-sm text-muted-foreground">
      <p>© {currentYear} Admin Portal. All rights reserved.</p>
    </footer>
  )
}
