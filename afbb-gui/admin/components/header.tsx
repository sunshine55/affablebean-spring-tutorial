import type { ReactNode } from "react"
import Link from "next/link"
import { Building, LogOut } from "lucide-react"
import { Button } from "@/components/ui/button"

interface HeaderProps {
  children?: ReactNode
}

export default function Header({ children }: HeaderProps) {
  return (
    <header className="sticky top-0 z-30 flex h-16 items-center justify-between border-b bg-background px-4 md:px-6">
      <div className="flex items-center gap-2">
        {children}
        <Link href="/" className="flex items-center gap-2">
          <Building className="h-6 w-6" />
          <span className="text-lg font-bold">Admin Portal</span>
        </Link>
      </div>

      <div className="flex items-center gap-4">
        <span className="hidden text-sm text-muted-foreground md:inline-block">admin@example.com</span>
        <Button variant="outline" size="sm" className="gap-1">
          <LogOut className="h-4 w-4" />
          <span>Logout</span>
        </Button>
      </div>
    </header>
  )
}
