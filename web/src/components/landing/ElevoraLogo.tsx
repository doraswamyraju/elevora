import React from 'react';

interface LogoProps {
  className?: string;
  size?: 'sm' | 'md' | 'lg';
  showSubtitle?: boolean;
}

export const ElevoraLogo: React.FC<LogoProps> = ({ 
  className = '', 
  size = 'md',
  showSubtitle = false 
}) => {
  const iconSizes = {
    sm: 'w-7 h-7',
    md: 'w-9 h-9',
    lg: 'w-12 h-12'
  };

  const textSizes = {
    sm: 'text-lg',
    md: 'text-2xl',
    lg: 'text-3xl'
  };

  return (
    <div className={`flex items-center gap-2.5 select-none ${className}`}>
      {/* Exact 3 slanted gold bars logo icon from the master image */}
      <svg 
        className={`${iconSizes[size]} shrink-0`} 
        viewBox="0 0 40 36" 
        fill="none" 
        xmlns="http://www.w3.org/2000/svg"
      >
        {/* Top bar - slanted right parallelogram */}
        <polygon 
          points="8,4 38,4 32,11 2,11" 
          fill="#F5A623" 
        />
        {/* Middle bar - slightly shorter parallelogram */}
        <polygon 
          points="7,15 29,15 24,22 2,22" 
          fill="#F5A623" 
        />
        {/* Bottom bar - slanted parallelogram */}
        <polygon 
          points="6,26 36,26 30,33 0,33" 
          fill="#F5A623" 
        />
      </svg>

      {/* Brand Text */}
      <div className="flex flex-col leading-none">
        <div className="flex items-center">
          <span className={`font-extrabold text-white tracking-tight ${textSizes[size]} font-display`}>
            Elevora
          </span>
          <span className="text-amber-400 text-xs font-bold ml-0.5 -mt-2">™</span>
        </div>
        {showSubtitle && (
          <span className="text-[10px] text-slate-400 font-medium tracking-wide mt-1">
            Professional English Learning Platform
          </span>
        )}
      </div>
    </div>
  );
};
